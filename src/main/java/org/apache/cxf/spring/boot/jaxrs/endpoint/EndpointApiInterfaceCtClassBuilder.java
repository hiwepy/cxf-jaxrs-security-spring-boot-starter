package org.apache.cxf.spring.boot.jaxrs.endpoint;

import org.apache.commons.lang3.builder.Builder;
import org.apache.cxf.spring.boot.jaxrs.endpoint.ctweb.HttpMethodEnum;
import org.apache.cxf.spring.boot.jaxrs.endpoint.ctweb.RestBound;
import org.apache.cxf.spring.boot.jaxrs.endpoint.ctweb.RestMethod;
import org.apache.cxf.spring.boot.jaxrs.endpoint.ctweb.RestParam;
import org.apache.cxf.spring.boot.jaxrs.endpoint.ctweb.RestProduce;
import org.apache.cxf.spring.boot.jaxrs.utils.EndpointApiUtils;

import com.github.vindell.javassist.utils.JavassistUtils;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtField;
import javassist.CtMethod;
import javassist.CtNewMethod;
import javassist.Modifier;
import javassist.NotFoundException;
import javassist.bytecode.AnnotationsAttribute;
import javassist.bytecode.ClassFile;
import javassist.bytecode.ConstPool;

/**
 * 
 * 动态构建ws接口
 * <p>http://www.cnblogs.com/sunfie/p/5154246.html</p>
 * <p>http://blog.csdn.net/youaremoon/article/details/50766972</p>
 * <p>https://blog.csdn.net/tscyds/article/details/78415172</p>
 * <p>https://my.oschina.net/GameKing/blog/794580</p>
 * <p>http://wsmajunfeng.iteye.com/blog/1912983</p>
 */
public class EndpointApiInterfaceCtClassBuilder implements Builder<CtClass> {
	
	// 构建动态类
	private ClassPool pool = null;
	private CtClass declaring  = null;
	private ClassFile ccFile = null;
	
	//private Loader loader = new Loader(pool);
	
	public EndpointApiInterfaceCtClassBuilder(final String classname) throws CannotCompileException, NotFoundException  {
		this(JavassistUtils.getDefaultPool(), classname);
	}
	
	public EndpointApiInterfaceCtClassBuilder(final ClassPool pool, final String classname) throws CannotCompileException, NotFoundException {
		
		this.pool = pool;
		this.declaring = EndpointApiUtils.makeInterface(pool, classname);
		
		/* 指定 Cloneable 作为动态接口的父类 */
		CtClass superclass = pool.get(Cloneable.class.getName());
		declaring.setSuperclass(superclass);
		
		this.ccFile = this.declaring.getClassFile();
	}
	
	public EndpointApiInterfaceCtClassBuilder annotationForType(final RestProduce produce) {
		return this.annotationForType(produce.getPath(), produce.getMediaTypes());
	}
	
	public EndpointApiInterfaceCtClassBuilder annotationForType(final String path, final String... mediaTypes) {

		ConstPool constPool = this.ccFile.getConstPool();
		 
		// 添加类注解 @Path
		AnnotationsAttribute classAttr = new AnnotationsAttribute(constPool, AnnotationsAttribute.visibleTag);
		
		// 设置类 @Path 注解
		classAttr.addAnnotation(EndpointApiUtils.annotPath(constPool, path));
		
		// 设置类 @Produces 注解
		if(mediaTypes != null && mediaTypes.length > 0) {
	        classAttr.addAnnotation(EndpointApiUtils.annotProduces(constPool, mediaTypes));
		}
		
		ccFile.addAttribute(classAttr);
		
		return this;
	}
	
	/**
	 * 通过给动态类增加 <code>@WebBound</code>注解实现，数据的绑定
	 */
	public EndpointApiInterfaceCtClassBuilder annotationForType(final String uid, final String json) {
		return annotationForType(new RestBound(uid, json));
	}
	
	/**
	 * 通过给动态类增加 <code>@WebBound</code>注解实现，数据的绑定
	 */
	public EndpointApiInterfaceCtClassBuilder annotationForType(final RestBound bound) {

		ConstPool constPool = this.ccFile.getConstPool();
		// 添加类注解 @WebBound
		AnnotationsAttribute classAttr = new AnnotationsAttribute(constPool, AnnotationsAttribute.visibleTag);
		classAttr.addAnnotation(EndpointApiUtils.annotWebBound(constPool, bound));
		ccFile.addAttribute(classAttr);
		
		return this;
	}
	
	/**
     * Compiles the given source code and creates a field.
     * Examples of the source code are:
     * 
     * <pre>
     * "public String name;"
     * "public int k = 3;"</pre>
     *
     * <p>Note that the source code ends with <code>';'</code>
     * (semicolon).
     *
     * @param src               the source text.
     */
	public <T> EndpointApiInterfaceCtClassBuilder makeField(final String src) throws CannotCompileException {
		//创建属性
        declaring.addField(CtField.make(src, declaring));
		return this;
	}
	
	public <T> EndpointApiInterfaceCtClassBuilder newField(final Class<T> fieldClass, final String fieldName, final String fieldValue) throws CannotCompileException, NotFoundException {
		
		// 检查字段是否已经定义
		if(JavassistUtils.hasField(declaring, fieldName)) {
			return this;
		}
		
		/** 添加属性字段 */
		CtField field = new CtField(this.pool.get(fieldClass.getName()), fieldName, declaring);
        field.setModifiers(Modifier.PUBLIC);

        //新增Field
        declaring.addField(field, "\"" + fieldValue + "\"");
        
		return this;
	}
	
	public <T> EndpointApiInterfaceCtClassBuilder removeField(final String fieldName) throws NotFoundException {
		
		// 检查字段是否已经定义
		if(!JavassistUtils.hasField(declaring, fieldName)) {
			return this;
		}
		
		declaring.removeField(declaring.getDeclaredField(fieldName));
		
		return this;
	}
	
	public <T> EndpointApiInterfaceCtClassBuilder abstractMethod(final Class<T> rtClass, final HttpMethodEnum method, final String name,final String path, final RestBound bound, RestParam<?>... params) throws CannotCompileException, NotFoundException {
		return this.abstractMethod(rtClass , new RestMethod(method, name, path), bound, params);
	}
	
	public <T> EndpointApiInterfaceCtClassBuilder abstractMethod(final Class<T> rtClass, final HttpMethodEnum method, final String name,final String path, RestParam<?>... params) throws CannotCompileException, NotFoundException {
		return this.abstractMethod(rtClass , new RestMethod(method, name, path), params);
	}
	
	/**
	 * 
	 * 根据参数构造一个新的方法
	 * @param result ：返回结果信息
	 * @param method ：方法注释信息
	 * @param bound  ：方法绑定数据信息
	 * @param params ： 参数信息
	 * @return
	 * @throws CannotCompileException
	 * @throws NotFoundException 
	 */ 
	public <T> EndpointApiInterfaceCtClassBuilder abstractMethod(final Class<T> rtClass, final RestMethod method, final RestBound bound, RestParam<?>... params) throws CannotCompileException, NotFoundException {
			      
		ConstPool constPool = this.ccFile.getConstPool();
		
		// 创建抽象方法
		CtClass returnType = rtClass != null ? pool.get(rtClass.getName()) : CtClass.voidType;
		CtClass[] exceptions = new CtClass[] { pool.get("java.lang.Exception") };
		// 方法参数
		CtClass[] parameters = EndpointApiUtils.makeParams(pool, params);
		CtMethod ctMethod = null;
		// 有参方法
		if(parameters != null && parameters.length > 0) {
			ctMethod = CtNewMethod.abstractMethod(returnType, method.getName(), parameters , exceptions, declaring);
		} 
		// 无参方法 
		else {
			ctMethod = CtNewMethod.abstractMethod(returnType, method.getName(), null , exceptions, declaring);
		}
		
		// 为方法添加 @HttpMethod、 @GET、 @POST、 @PUT、 @DELETE、 @PATCH、 @HEAD、 @OPTIONS、@Path、、@Consumes、@RestBound、@RestParam 注解
        EndpointApiUtils.methodAnnotations(ctMethod, constPool, method, bound, params);
        
        //新增方法
        declaring.addMethod(ctMethod);
        
        return this;
	}
	
	public <T> EndpointApiInterfaceCtClassBuilder abstractMethod(final Class<T> rtClass, final RestMethod method, RestParam<?>... params) throws CannotCompileException, NotFoundException {
		return this.abstractMethod(rtClass, method, null, params);
	}
	
	public <T> EndpointApiInterfaceCtClassBuilder abstractMethod(final HttpMethodEnum method, final String name,final String path, RestParam<?>... params) throws CannotCompileException, NotFoundException {
		return this.abstractMethod(null , new RestMethod(method, name, path), null, params);
	}
	
	public <T> EndpointApiInterfaceCtClassBuilder abstractMethod(final HttpMethodEnum method, final String name, final String path, final RestBound bound, RestParam<?>... params) throws CannotCompileException, NotFoundException {
		return this.abstractMethod(null, new RestMethod(method, name, path), bound, params);
	}
	
	public <T> EndpointApiInterfaceCtClassBuilder abstractMethod(final RestMethod method, final RestBound bound, RestParam<?>... params) throws CannotCompileException, NotFoundException {
		return this.abstractMethod(null, method, bound, params);
	}
	
	public <T> EndpointApiInterfaceCtClassBuilder abstractMethod(final RestMethod method, RestParam<?>... params) throws CannotCompileException, NotFoundException {
		return this.abstractMethod(null, method, null, params);
	}
	
	public <T> EndpointApiInterfaceCtClassBuilder removeMethod(final String methodName, RestParam<?>... params) throws NotFoundException {
		
		// 有参方法
		if(params != null && params.length > 0) {
			
			// 方法参数
			CtClass[] parameters = EndpointApiUtils.makeParams(pool, params);
			
			// 检查方法是否已经定义
			if(!JavassistUtils.hasMethod(declaring, methodName, parameters)) {
				return this;
			}
			
			declaring.removeMethod(declaring.getDeclaredMethod(methodName, parameters));
			
		}
		else {
			
			// 检查方法是否已经定义
			if(!JavassistUtils.hasMethod(declaring, methodName)) {
				return this;
			}
			
			declaring.removeMethod(declaring.getDeclaredMethod(methodName));
			
		}
		
		return this;
	}
	
	@Override
	public CtClass build() {
        return declaring;
	}
	
	/**
	 * 
	 * javassist在加载类时会用Hashtable将类信息缓存到内存中，这样随着类的加载，内存会越来越大，甚至导致内存溢出。如果应用中要加载的类比较多，建议在使用完CtClass之后删除缓存
	 * @author 		： <a href="https://github.com/vindell">vindell</a>
	 * @return
	 * @throws CannotCompileException
	 */
	public Class<?> toClass() throws CannotCompileException {
        try {
        	// 通过类加载器加载该CtClass
			return declaring.toClass();
		} finally {
			// 将该class从ClassPool中删除
			declaring.detach();
		} 
	}

}