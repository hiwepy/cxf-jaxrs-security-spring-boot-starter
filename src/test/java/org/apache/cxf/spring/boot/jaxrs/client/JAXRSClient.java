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
package org.apache.cxf.spring.boot.jaxrs.client;

/**
 * TODO
 * @author 		： <a href="https://github.com/vindell">vindell</a>
 */

import java.util.ArrayList;
import java.util.List;

import org.apache.cxf.jaxrs.client.JAXRSClientFactory;
import org.apache.cxf.spring.boot.jaxrs.Customer;
import org.apache.cxf.spring.boot.jaxrs.CustomerService;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;

public class JAXRSClient {

    public static void main(String[] args) {
        String baseAddress = "http://localhost:8080/ws/rest";

        List<Object> providerList = new ArrayList<Object>();
        providerList.add(new JacksonJsonProvider());

        CustomerService productService = JAXRSClientFactory.create(baseAddress, CustomerService.class, providerList);
        Customer productList = productService.findCustomerById("xx");
       
    }
}