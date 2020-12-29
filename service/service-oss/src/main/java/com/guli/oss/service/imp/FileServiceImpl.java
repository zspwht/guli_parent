package com.guli.oss.service.imp;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.CannedAccessControlList;
import com.guli.oss.service.FileService;
import com.guli.oss.util.ConstantPropertiesUtil;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;
@Service
public class FileServiceImpl implements FileService {
    @Override
    public String upload(MultipartFile file) {
        String endpoint = ConstantPropertiesUtil.END_POINT;
        String keyId = ConstantPropertiesUtil.KEY_ID;
        String keySecret = ConstantPropertiesUtil.KEY_SECRET;
        String bucketName = ConstantPropertiesUtil.BUCKET_NAME;
        String uploadUrl = null;
        try {
            OSS oss = new OSSClientBuilder().build(endpoint, keyId, keySecret);
            if (!oss.doesBucketExist(bucketName)) {
                oss.createBucket(bucketName);
                oss.setBucketAcl(bucketName, CannedAccessControlList.PublicRead);
            }

            InputStream inputStream = file.getInputStream();
            String filePath = new DateTime().toString("yyyy/MM/dd");
            String origina = file.getOriginalFilename();
            String fileName = UUID.randomUUID().toString().replace("-","");
            String fileType = origina.substring(origina.lastIndexOf("."));
            String newName = fileName+fileType;
            String fileUrl = filePath+"/"+newName;
            oss.putObject(bucketName,fileUrl,inputStream);
            oss.shutdown();
            uploadUrl = endpoint+"/"+fileUrl;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return uploadUrl;
    }
}
