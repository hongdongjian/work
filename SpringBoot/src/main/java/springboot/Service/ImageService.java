package springboot.Service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import springboot.Dao.ImageDao;
import springboot.Entity.Img;

import javax.annotation.Resource;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by lenovo on 2017/3/14.
 */
@Transactional
@Service
public class ImageService extends BaseService<Integer,Img> {
    @Resource
    private ImageDao imageDao;

    public boolean doUpload(MultipartFile file, String filepath) {
        try{
            byte[] bytes=file.getBytes();
            BufferedOutputStream bufferedOutputStream=new BufferedOutputStream(new FileOutputStream(new File(filepath)));
            bufferedOutputStream.write(bytes);
            bufferedOutputStream.flush();
            bufferedOutputStream.close();
            Img img = new Img();
            img.setFileName(filepath);
            imageDao.save(img);
        }catch (IOException e)
        {
            return false;
        }
        return true;
    }
}
