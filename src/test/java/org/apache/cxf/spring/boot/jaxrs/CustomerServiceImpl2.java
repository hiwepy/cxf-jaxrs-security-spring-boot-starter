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


import java.util.Calendar;  
 
 
public class CustomerServiceImpl2 implements CustomerService2 {    
       
   public Customer findCustomerById(String id) {    
       Customer customer = new Customer();    
       customer.setId(id);    
       customer.setName("xiaojing"+id);    
       customer.setBirthday(Calendar.getInstance().getTime());    
       System.out.println(">>>>>>>>>>>>>服务端信息："+customer);  
       return customer;    
   }    
       
   public Customer findCustomerByName(String name) {    
       Customer customer = new Customer();    
       customer.setId(name);    
       customer.setName(name);    
       customer.setBirthday(Calendar.getInstance().getTime());   
       System.out.println(">>>>>>>>>>>>>服务端信息："+customer);   
       return customer;    
   }    
}    