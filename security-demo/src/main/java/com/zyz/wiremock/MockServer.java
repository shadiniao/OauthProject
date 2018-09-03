package com.zyz.wiremock;


import com.github.tomakehurst.wiremock.client.WireMock;

import org.aspectj.util.FileUtil;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;


/**
 * 2018/9/3.
 *
 * @author zhangyizhi
 */
public class MockServer {
    public static void main(String[] args) throws IOException {
        WireMock.configureFor(8062);
        WireMock.removeAllMappings(); // 每次执行前都清空之前的配置
        mock("/order/1", "01.txt");
        mock("/order/2", "02.txt");
    }

    public static void mock(String url, String fileName) throws IOException {
        ClassPathResource resource = new ClassPathResource("mock/response/" + fileName);
        String content = FileUtil.readAsString(resource.getFile());

        WireMock.stubFor(
                WireMock.get(
                        WireMock.urlPathEqualTo(url))
                        .willReturn(
                                WireMock.aResponse()
                                        .withBody(content)
                                        .withStatus(200)
                        ));
    }
}
