/*
    1、同步请求和异步请求的区别：
        同步请求：浏览器窗口发出的请求，响应信息返回到浏览器窗口，所以会进行全局刷新
        异步请求：ajax发出的请求，相应信息返回到ajax的回调函数，既可以进行全局刷新，也可以进行局部刷新

        小结：如果需要进行全局刷新，推荐使用同步请求，当然也可以使用异步请求
            如果需要进行异步刷新，只能使用异步请求
            如果极可能进行全局刷新，也可能进行局部刷新，也是只能使用异步请求。

    2、mybatis逆向工程：
        1）简介：根据表生成mapper层三部分代码：实体类、mapper接口、映射文件
        2）使用mybatis逆向工程：
            a）创建工程：crm-mybatisGenerator
            b）添加插件：

                <build>
                    <plugins>
                        <!--myBatis逆向工程插件-->
                        <plugin>
                            <groupId>org.mybatis.generator</groupId>
                            <artifactId>mybatis-generator-maven-plugin</artifactId>
                            <version>1.3.2</version>
                            <configuration>
                                <verbose>true</verbose>
                                <overwrite>true</overwrite>
                            </configuration>
                        </plugin>
                    </plugins>
                </build>

         3）添加mybatis逆向工程的配置文件：数据库连接信息、代码保存的目录、表的信息

         4）运行mybatis的逆向工程，根据指定的表生成java代码，并且保存到指定的目录中
 */
public class ReadMe {
}
