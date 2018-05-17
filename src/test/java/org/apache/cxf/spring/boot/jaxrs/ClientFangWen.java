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
package org.apache.cxf.spring.boot.jaxrs;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.springframework.http.HttpEntity;  
  
  
/** 
 * @author jingguoqiang 
 * @desc  org.apache.cxf jar包写的客户端 
 */  
public class ClientFangWen {  
      
    /**  
    * @Description: 测试没有和spring集成的  
    * @author: jingguoqiang 
    * @date 2015-9-19 下午4:20:32  
    */   
    @Test  
    public void testClient1() {    
        HttpClient httpclient = new DefaultHttpClient();    
        HttpGet httpget = new HttpGet("http://localhost:9000/ws/jaxrs/customer/1/info");  
        String result = null;  
        HttpResponse response = null;  
        try {  
            response = httpclient.execute(httpget);  
            // 获取响应实体  
            HttpEntity entity = response.getEntity();  
            if (response.getStatusLine().getStatusCode() == 200) {  
                result = EntityUtils.toString(entity);  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {    
            try {     
                httpclient.getConnectionManager().shutdown();     
            } catch (Exception ignore) {    
            }    
        }  
        System.out.println("客户端得到信息"+result);          
    }  
    @Test  
    public void testClient2() {    
        HttpClient httpclient = new DefaultHttpClient();    
        HttpGet httpget = new HttpGet("http://localhost:9000/ws/jaxrs/customer/search?name=damaha");  
        String result = null;  
        HttpResponse response = null;  
        try {  
            response = httpclient.execute(httpget);  
            // 获取响应实体  
            HttpEntity entity = response.getEntity();  
            if (response.getStatusLine().getStatusCode() == 200) {  
                result = EntityUtils.toString(entity);  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {    
            try {     
                httpclient.getConnectionManager().shutdown();     
            } catch (Exception ignore) {    
            }    
        }  
        System.out.println("客户端得到信息"+result);          
    }  
}  