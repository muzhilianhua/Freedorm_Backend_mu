package com.ruoyi.common.utils.file;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;

import org.apache.poi.util.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.model.COSObject;
import com.qcloud.cos.model.GetObjectRequest;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.cos.CosClientFactory;

/**
 * 图片处理工具类
 */
@Component
public class ImageUtils {
    private static final Logger log = LoggerFactory.getLogger(ImageUtils.class);

    public static byte[] getImage(String imagePath) {
        InputStream is = getFile(imagePath);
        try {
            return IOUtils.toByteArray(is);
        } catch (Exception e) {
            log.error("图片加载异常 {}", e);
            return null;
        } finally {
            IOUtils.closeQuietly(is);
        }
    }

    public static InputStream getFile(String imagePath) {
        try {
            byte[] result = readFile(imagePath);
            result = Arrays.copyOf(result, result.length);
            return new ByteArrayInputStream(result);
        } catch (Exception e) {
            log.error("获取图片异常 {}", e);
        }
        return null;
    }

    /**
     * 读取文件为字节数据
     *
     * @param url 地址（COS URL或本机URL）
     * @return 字节数据
     */
    public static byte[] readFile(String url) {
        COSClient cosClient = CosClientFactory.getCosClient();
        String bucketName = CosClientFactory.getBucketName();
        InputStream in = null;

        try {
            if (url.startsWith("http")) {
                // 网络地址，假设是COS的URL
                String key = FileUtils.getKeyFromUrl(url);
                GetObjectRequest getObjectRequest = new GetObjectRequest(bucketName, key);
                COSObject cosObject = cosClient.getObject(getObjectRequest);
                in = cosObject.getObjectContent();
            } else {
                // 如果需要支持本机地址，可以保留本地读取逻辑
                // 否则，根据需求调整
                // 例如：
                // return super.readFile(url);
                throw new UnsupportedOperationException("仅支持COS的HTTP地址");
            }
            return IOUtils.toByteArray(in);
        } catch (Exception e) {
            log.error("获取文件路径异常 {}", e);
            return null;
        } finally {
            IOUtils.closeQuietly(in);
        }
    }

    /**
     * 获取图片的COS公共URL
     *
     * @param imagePath 文件的COS URL
     * @return 图片的COS公共URL
     */
    public static String getImageUrl(String imagePath) {
        // 由于COS公有读，直接返回URL
        return imagePath;
    }
}
