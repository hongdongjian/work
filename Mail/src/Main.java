import Dto.Mail;
import Dto.Props;
import Util.MailUtil;

public class Main {
    public static void main(String[] args) {
        Props props = new Props();
        props.setHost("smtp.qq.com");
        props.setFrom("1427594010@qq.com");
        props.setUsername("1427594010@qq.com");
        props.setPassword(""); //qq邮箱授权码

        Mail mail = new Mail();
        mail.setContent("邮件测试内容");
        mail.setSubject("邮件测试");
        mail.setToMails(new String[]{"1427594010@qq.com"});
        //非ssl
        MailUtil.sendMail(props,mail,"25",false);
        //ssl
        MailUtil.sendMail(props,mail,"465",true);
    }
}
