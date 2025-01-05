package com.ruoyi.common.utils.cos;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.region.Region;
import com.qcloud.cos.http.HttpProtocol;

/**
 * COS客户端工厂类，从配置文件中读取配置信息
 */
@Component
public class CosClientFactory {

    private static COSClient cosClient;
    private static String bucketName;
    private static String domain;

    @Value("${cos.secretId}")
    private String secretId;

    @Value("${cos.secretKey}")
    private String secretKey;

    @Value("${cos.region}")
    private String regionName;

    @Value("${cos.bucket}")
    private String bucket;

    @Value("${cos.domain}")
    private String cosDomain;

    @PostConstruct
    public void init() {
        // 初始化COS客户端
        COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);
        ClientConfig clientConfig = new ClientConfig(new Region(regionName));
        clientConfig.setHttpProtocol(HttpProtocol.https); // 使用HTTPS

        cosClient = new COSClient(cred, clientConfig);
        bucketName = bucket;
        domain = cosDomain;
    }

    /**
     * 获取COSClient实例
     *
     * @return COSClient
     */
    public static COSClient getCosClient() {
        return cosClient;
    }

    /**
     * 获取Bucket名称
     *
     * @return Bucket名称
     */
    public static String getBucketName() {
        return bucketName;
    }

    /**
     * 获取COS的域名
     *
     * @return 域名
     */
    public static String getDomain() {
        return domain;
    }

    /**
     * 关闭COS客户端
     */
    public static void shutdown() {
        if (cosClient != null) {
            cosClient.shutdown();
        }
    }
}
