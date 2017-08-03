package springboot.Controller;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import springboot.Service.ImageService;

import javax.annotation.Resource;

/**
 * Created by lenovo on 2017/3/13.
 */
@Controller
public class IndexController {

    @Resource
    ImageService imageService;

    @RequestMapping("/")
    public ModelAndView getIndexHtml() {
        System.out.println("index html");
        return new ModelAndView("index.html");
    }

    /**
     * 处理url:/upload的请求
     * @param file
     * @param request
     * @return
     */
    @RequestMapping(value="/upload", method= RequestMethod.POST)
    @ResponseBody
    public String handleFileUpload(@RequestParam("file") MultipartFile file,HttpServletRequest request) {
        String realPath = request.getSession().getServletContext().getRealPath("/image");
        if(!file.isEmpty()) {
            String filename=System.currentTimeMillis()+file.getOriginalFilename();
            realPath=realPath+"/"+filename;
            if(!imageService.doUpload(file,realPath))
            {
                return "保存失败";
            }
        }
        else
        {
            return "保存失败";
        }
        return "保存成功";
    }
}
