/*
 * Copyright 2011 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package groovy.sql.builder

import groovy.sql.Sql
import javax.naming.Context
import javax.naming.InitialContext
import javax.sql.DataSource
import org.h2.jdbcx.JdbcDataSource

/**
 *
 *
 * @author Benjamin Muschko
 */
class GroovySqlHandler {
    static final DEFAULT_URL = 'jdbc:h2:~/cityalmanac'
    static final DEFAULT_USERNAME = 'sa'
    static final DEFAULT_PASSWORD = ''
    static final DEFAULT_DRIVER_CLASS_NAME = 'org.h2.Driver'

    static createDriverManagerSql(String url = DEFAULT_URL, String username = DEFAULT_USERNAME,
                                                 String password = DEFAULT_PASSWORD, String driverClassName = DEFAULT_DRIVER_CLASS_NAME) {
        Sql.newInstance(url, username, password, driverClassName)
    }

    static createDataSourceSql(url = DEFAULT_URL, username = DEFAULT_USERNAME, password = DEFAULT_PASSWORD) {
        DataSource dataSource = new JdbcDataSource()
        dataSource.URL = url
        dataSource.user = username
        dataSource.password = password
        new Sql(dataSource)
    }

    static createJndiDataSourceSql(String jndiName) {
        Context context = new InitialContext()
        DataSource dataSource = context.lookup(jndiName)
        new Sql(dataSource)
    }
}