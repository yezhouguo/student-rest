/*
 * Copyright 2017 the original author or authors.
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
package ye.studentrest;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;


@Component
class DatabaseLoader{

	/**
	 * Use Spring to inject a {@link EmployeeRepository} that can then load data.
	 * Since this will run only after the app is operational, the database will be
	 * up.
	 *
	 * @param repository
	 */
	@Bean
    CommandLineRunner init(StudentRepository repository) 
    {
		return args -> {
			repository.save(new Student("赵有才", "男", "19950305", "山东", "计算机"));
            repository.save(new Student("钱大拿", "男", "19970211", "浙江", "信管"));
            repository.save(new Student("孙子兵法", "女", "19990712", "福建", "地海"));
            repository.save(new Student("李子熟了", "女", "19960221", "江苏", "天文"));
		};
	}

}
