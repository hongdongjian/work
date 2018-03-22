import Dto.Mail;
import Dto.Props;
import Util.MailUtil;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        Props props = new Props();
        props.setHost("mail.pwalloy.com");
        props.setFrom("dspt@bodewires.com");
        props.setUsername("dspt@bodewires.com");
        props.setPassword(""); //邮箱授权码

        Mail mail = new Mail();
        mail.setContent("邮件测试内容");
        mail.setSubject("邮件测试");
        mail.setToMails(new String[]{"1427594010@qq.com"});
        File file = new File("/Users/hongdongjian/java_error_in_idea_260.log");
        if (file.exists()) {
            System.out.println("exit");
            mail.setFile(file);
        }else {
            System.out.println("not exit");
        }
//        MailUtil.sendMailWithFile(props,mail,"25",false);
        MailUtil.sendMailWithFile(props,mail,"465",true);
        //非ssl
//        MailUtil.sendMail(props,mail,"25",false);
        //ssl
//        MailUtil.sendMail(props,mail,"465",true);
    }
}
