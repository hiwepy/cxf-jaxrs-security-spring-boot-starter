/*
 * Copyright (c) 2018, vindell (https://github.com/vindell).
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.apache.cxf.spring.boot.jaxrs.utils;

import java.lang.reflect.InvocationHandler;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.CookieParam;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.HEAD;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.HttpMethod;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.PATCH;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.cxf.spring.boot.jaxrs.annotation.WebBound;
import org.apache.cxf.spring.boot.jaxrs.endpoint.ctweb.HttpParamEnum;
import org.apache.cxf.spring.boot.jaxrs.endpoint.ctweb.RestBound;
import org.apache.cxf.spring.boot.jaxrs.endpoint.ctweb.RestMethod;
import org.apache.cxf.spring.boot.jaxrs.endpoint.ctweb.RestParam;
import org.springframework.util.StringUtils;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtConstructor;
import javassist.CtMethod;
import javassist.CtNewConstructor;
import javassist.NotFoundException;
import javassist.bytecode.AnnotationsAttribute;
import javassist.bytecode.ConstPool;
import javassist.bytecode.ParameterAnnotationsAttribute;
import javassist.bytecode.annotation.Annotation;
import javassist.bytecode.annotation.ArrayMemberValue;
import javassist.bytecode.annotation.StringMemberValue;

public class EndpointApiUtils {

	public static CtClass makeClass(final ClassPool pool, final String classname)
			throws NotFoundException, CannotCompileException {

		CtClass declaring = pool.getOrNull(classname);
		if (null == declaring) {
			declaring = pool.makeClass(classname);
		}
		
		/*
		 * 当 ClassPool.doPruning=true的时候，Javassist 在CtClass object被冻结时，会释放存储在ClassPool对应的数据。
		 * 这样做可以减少javassist的内存消耗。默认情况ClassPool.doPruning=false。
		 */
		declaring.stopPruning(true);

		return declaring;
	}
	
	public static CtConstructor defaultConstructor(final CtClass declaring) throws CannotCompileException   {
		// 默认添加无参构造器  
		CtConstructor cons = new CtConstructor(null, declaring);  
		cons.setBody("{}");  
    	return cons;
	}
	
	public static CtConstructor makeConstructor(final ClassPool pool, final CtClass declaring) throws NotFoundException, CannotCompileException  {

		// 添加有参构造器，注入回调接口
    	CtClass[] parameters = new CtClass[] {pool.get(InvocationHandler.class.getName())};
    	CtClass[] exceptions = new CtClass[] { pool.get("java.lang.Exception") };
    	return CtNewConstructor.make(parameters, exceptions, "{super($1);}", declaring);
    	
	}

	public static CtClass makeInterface(final ClassPool pool, final String classname)
			throws NotFoundException, CannotCompileException {

		CtClass declaring = pool.getOrNull(classname);
		if (null == declaring) {
			declaring = pool.makeInterface(classname);
		}

		// 当 ClassPool.doPruning=true的时候，Javassist 在CtClass
		// object被冻结时，会释放存储在ClassPool对应的数据。这样做可以减少javassist的内存消耗。默认情况ClassPool.doPruning=false。
		declaring.stopPruning(true);

		return declaring;
	}
	

	public static <T> void setSuperclass(final ClassPool pool, final CtClass declaring, final Class<T> clazz)
			throws Exception {

		/* 获得 JaxwsHandler 类作为动态类的父类 */
		CtClass superclass = pool.get(clazz.getName());
		declaring.setSuperclass(superclass);

	}
	
	public static CtClass[] makeParams(final ClassPool pool, RestParam<?>... params) throws NotFoundException {
		// 无参
		if(params == null || params.length == 0) {
			return null;
		}
		// 方法参数
		CtClass[] parameters = new CtClass[params.length];
		for(int i = 0;i < params.length; i++) {
			parameters[i] = pool.get(params[i].getType().getName());
		}

		return parameters;
	}
	
	/**
	 * 构造  @Path 注解
	 */
	public static Annotation annotPath(final ConstPool constPool, String path) {
		
		Annotation annot = new Annotation(Path.class.getName(), constPool);
		annot.addMemberValue("value", new StringMemberValue(path, constPool));
		
		return annot;
	}
	
	/**
	 * 构造  @Produces 注解
	 */
	public static Annotation annotProduces(final ConstPool constPool, String... mediaTypes) {

		Annotation annot = new Annotation(Produces.class.getName(), constPool);
		if(mediaTypes != null && mediaTypes.length > 0) {
			ArrayMemberValue arr = new ArrayMemberValue(constPool);
			StringMemberValue[] mediaMembers = new StringMemberValue[mediaTypes.length];
			for (int i = 0; i < mediaMembers.length; i++) {
				mediaMembers[i] = new StringMemberValue(mediaTypes[i], constPool);
			}
	        arr.setValue(mediaMembers);
	        annot.addMemberValue("value", arr);
		}
		
		return annot;
	}
	
	/**
	 * 为方法添加 @HttpMethod、 @GET、 @POST、 @PUT、 @DELETE、 @PATCH、 @HEAD、 @OPTIONS、@Path、、@Consumes、@Produces、@RestBound、@RestParam 注解
	 * @author 		： <a href="https://github.com/vindell">vindell</a>
	 * @param ctMethod
	 * @param constPool
	 * @param result
	 * @param method
	 * @param bound
	 * @param params
	 * @see HttpMethod
	 * @see GET
	 * @see POST
	 * @see PUT
	 * @see DELETE
	 * @see PATCH
	 * @see HEAD
	 * @see OPTIONS
	 */
	public static <T> void methodAnnotations(final CtMethod ctMethod, final ConstPool constPool, final RestMethod method, final RestBound bound, RestParam<?>... params) {
		
		// 添加方法注解
        AnnotationsAttribute methodAttr = new AnnotationsAttribute(constPool, AnnotationsAttribute.visibleTag);
       
        // 添加 @WebBound 注解
        if (bound != null) {
        	methodAttr.addAnnotation(EndpointApiUtils.annotWebBound(constPool, bound));
        }
        
        // 添加 @GET、 @POST、 @PUT、 @DELETE、 @PATCH、 @HEAD、 @OPTIONS  注解
        methodAttr.addAnnotation(EndpointApiUtils.annotHttpMethod(constPool, method));
        
        // 添加 @Path 注解	        
        methodAttr.addAnnotation(EndpointApiUtils.annotPath(constPool, method.getPath()));
        
        // 添加 @Consumes 注解	
        if (ArrayUtils.isNotEmpty(method.getConsumes())) {
	        methodAttr.addAnnotation(EndpointApiUtils.annotConsumes(constPool, method.getConsumes()));
        }
        
        // 添加 @Produces 注解
        if (ArrayUtils.isNotEmpty(method.getMediaTypes())) {
        	methodAttr.addAnnotation(EndpointApiUtils.annotProduces(constPool, method.getMediaTypes()));
 		}
     		
        ctMethod.getMethodInfo().addAttribute(methodAttr);
        
        // 添加 @WebParam 参数注解
        if(params != null && params.length > 0) {
        	
        	ParameterAnnotationsAttribute parameterAtrribute = new ParameterAnnotationsAttribute(constPool, ParameterAnnotationsAttribute.visibleTag);
            Annotation[][] paramArrays = EndpointApiUtils.annotParams(constPool, params);
            parameterAtrribute.setAnnotations(paramArrays);
            ctMethod.getMethodInfo().addAttribute(parameterAtrribute);
            
        }
        
	}
	
	/**
	 * 设置方法体
	 * @throws CannotCompileException 
	 */
	public static void methodBody(final CtMethod ctMethod, final RestMethod method) throws CannotCompileException {
		
		// 构造方法体
		StringBuilder body = new StringBuilder(); 
        body.append("{\n");
        	body.append("if(getHandler() != null){\n");
        		body.append("Method method = this.getClass().getDeclaredMethod(\"" + method.getName() + "\", $sig);");
        		body.append("return ($r)getHandler().invoke($0, method, $args);");
        	body.append("}\n"); 
	        body.append("return null;\n");
        body.append("}"); 
        // 将方法的内容设置为要写入的代码，当方法被 abstract修饰时，该修饰符被移除。
        ctMethod.setBody(body.toString());
        
	}
	
	/**
	 * 设置方法异常捕获逻辑
	 * @throws NotFoundException 
	 * @throws CannotCompileException 
	 */
	public static void methodCatch(final ClassPool pool, final CtMethod ctMethod) throws NotFoundException, CannotCompileException {
		
		// 构造异常处理逻辑
        CtClass etype = pool.get("java.lang.Exception");
        ctMethod.addCatch("{ System.out.println($e); throw $e; }", etype);
        
	}
	
	/**
	 * 构造 @WebBound 注解
	 */
	public static Annotation annotWebBound(final ConstPool constPool, final RestBound bound) {
		
		Annotation annot = new Annotation(WebBound.class.getName(), constPool);
		annot.addMemberValue("uid", new StringMemberValue(bound.getUid(), constPool));
        if (StringUtils.hasText(bound.getJson())) {
        	annot.addMemberValue("json", new StringMemberValue(bound.getJson(), constPool));
        }
        
		return annot;
	}
	
	/**
	 * 根据参数 构造   @GET、 @POST、 @PUT、 @DELETE、 @PATCH、 @HEAD、 @OPTIONS 注解
	 */
	public static Annotation annotHttpMethod(final ConstPool constPool, final RestMethod method) {
		
		Annotation annot = null;
		switch (method.getMethod()) {
			case GET:{
				annot = new Annotation(GET.class.getName(), constPool);
			};break;
			case POST:{
				annot = new Annotation(POST.class.getName(), constPool);
			};break;
			case PUT:{
				annot = new Annotation(PUT.class.getName(), constPool);
			};break;
			case DELETE:{
				annot = new Annotation(DELETE.class.getName(), constPool);
			};break;
			case PATCH:{
				annot = new Annotation(PATCH.class.getName(), constPool);
			};break;
			case HEAD:{
				annot = new Annotation(HEAD.class.getName(), constPool);
			};break;
			case OPTIONS:{
				annot = new Annotation(OPTIONS.class.getName(), constPool);
			};break;
			default:{
				annot = new Annotation(GET.class.getName(), constPool);
			};break;
		}
		
		return annot;
	}
	
	/**
	 * 构造 @Consumes 注解
	 */
	public static <T> Annotation annotConsumes(final ConstPool constPool, String... consumes) {

		Annotation annot = new Annotation(Consumes.class.getName(), constPool);
		if (consumes != null && consumes.length > 0) {
			ArrayMemberValue arr = new ArrayMemberValue(constPool);
			StringMemberValue[] mediaMembers = new StringMemberValue[consumes.length];
			for (int i = 0; i < mediaMembers.length; i++) {
				mediaMembers[i] = new StringMemberValue(consumes[i], constPool);
			}
			arr.setValue(mediaMembers);
			annot.addMemberValue("value", arr);
		} else {

			ArrayMemberValue arr = new ArrayMemberValue(constPool);
			StringMemberValue[] mediaMembers = new StringMemberValue[] { new StringMemberValue("*/*", constPool) };
			arr.setValue(mediaMembers);
			annot.addMemberValue("value", arr);

		}

		return annot;
	}
	
	/**
	 * 构造 @BeanParam 、@CookieParam、@FormParam、@HeaderParam、@MatrixParam、@PathParam、@QueryParam 参数注解
	 */
	public static <T> Annotation[][] annotParams(final ConstPool constPool, RestParam<?>... params) {

		// 添加 @WebParam 参数注解
		if (params != null && params.length > 0) {

			Annotation[][] paramArrays = new Annotation[params.length][1];
			
			Annotation paramAnnot = null;
			for (int i = 0; i < params.length; i++) {
				
				switch (params[i].getFrom()) {
					case BEAN:{
						paramAnnot = new Annotation(BeanParam.class.getName(), constPool);
					};break;
					case COOKIE:{
						paramAnnot = new Annotation(CookieParam.class.getName(), constPool);
					};break;
					case FORM:{
						paramAnnot = new Annotation(FormParam.class.getName(), constPool);
					};break;
					case HEADER:{
						paramAnnot = new Annotation(HeaderParam.class.getName(), constPool);
					};break;
					case MATRIX:{
						paramAnnot = new Annotation(MatrixParam.class.getName(), constPool);
					};break;
					case PATH:{
						paramAnnot = new Annotation(PathParam.class.getName(), constPool);
					};break;
					case QUERY:{
						paramAnnot = new Annotation(QueryParam.class.getName(), constPool);
					};break;
					default:{
						paramAnnot = new Annotation(QueryParam.class.getName(), constPool);
					};break;
				}
				if(HttpParamEnum.BEAN.compareTo(params[i].getFrom()) != 0){
					paramAnnot.addMemberValue("value", new StringMemberValue(params[i].getName(), constPool));
				}
				
				// 有默认值
				if(StringUtils.hasText(params[i].getDef())) {
					
					paramArrays[i] = new Annotation[2];
					paramArrays[i][0] = paramAnnot;
					
					Annotation defAnnot = new Annotation(DefaultValue.class.getName(), constPool);
					defAnnot.addMemberValue("value", new StringMemberValue(params[i].getDef(), constPool));
					paramArrays[i][1] = paramAnnot;
					
				} else {
					paramArrays[i][0] = paramAnnot;
				}
				
			}
			
			return paramArrays;

		}
		return null;
	}
	
}
