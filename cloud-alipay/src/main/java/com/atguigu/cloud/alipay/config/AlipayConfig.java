package com.atguigu.cloud.alipay.config;

import java.io.FileWriter;
import java.io.IOException;

public class AlipayConfig {

    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    public static String app_id = "2021000117621739";

    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCfkRADSDMjQAoxkzFNUhCnl2FI2mubURRLjIVHOSabX8MsdlZTAAB5ihNaoYGay0U1rGRaMlfadAPWa96lCrQ82E2K/XzHbvKB0scL6fttq69QnifwWs0ifa1xPraNhf9BTxeP3gWvW9zToUifCo3hrE3YR9/mu0uHR53U/y5VnB9j6mbxwAgo3xrS7QBYPeBjqYEaTWzTyCtArUvSkgDamZRuFJSgvUiwMSFdk6nJsM+JgRDFnJSuPuYklObMmH6d4ymqS7jydEPDY3zpLyRhOfTBjJmGixC6avXk28Ly8Tc8g/gnQ3mXe9AdwDhRwwV3gD84+7XNs7vGMR9QiSWTAgMBAAECggEAJAFTn6n3AAk/Q5h3E39lCqv547qFdXKCJdn7aOrYb07Yd3vsVqY1dTdWzqiK2ibr4wSXUFLzx/NNumB0yMD1kpUsSiSQt1+tkOXCzElr1n9a+9H0KSQUDahWLqt7/csyocX7BxeOKNeNqKNIA7sZ8MY0eo0dVK8Gh+v9DUbXabGJlwsa5X61QCMB4fHphC6jERsDx06/42Y/EpE4feGryiMQI8pkmDymG4+wpCqJZmZLIo0qjFuIl3gijYVTtEexRV2iagTl7Nn+voKo4tBvAs4YA1yrK9pppHoe1MP0W35c1QMIDjauOoNNTzfzoZlb3+8YEaV62ZFuo4NYEjGUoQKBgQDO8DwQFH9rMo+FjqxHqD8iueIdu9lySsRGuv2kPxHHPG7NR/mKfGbO5DafRmkovlTyw5HUdvKAHItYIgtmMsWAiX4+WReWNTyAoLwZW2rOuJFDJZYsTSl5jpU55z1XV3zMwXJcwTXexuqHR0OtLHgNC3Bm2KYE8CQJc951MTbOPwKBgQDFZa5xuJgFwBZZ+eMKtPpkM+Mj5mg4irWaRvHekpAxGp7uo3cPW+uW8ghI07w9B3yFBlPUe24Y8ujE9khj+Ygch3+4kP2SQ1jwERxJE5hmxo3HHowpE+ReeoBvoePI4+hTtbjQ51sm4Jse1N888NtkAcnE1hgfiTcyjC18q7j7rQKBgQC5e476gsTq2rjV51n4KI/emmpYaGWxEd733uc17uoKgNp32YmMXZDw5JEpFj1vrZoKtszheXRG7E7hwjIhemSYn6SuUcDYfAMs7+VpfQ0xGTHq06fVMZx5Phn3epL61IoiJUpSZUPZ2MWiV3gLoo5Acv5e0NWOG4jRiC/fLyuGlQKBgAvNDmbCEGSXnT53z/j95NJEv9mPlr96m2zDvQX/YRtuaf3+S8nhRu5ZPeGMtv43ClyxiiVz05suOng7+QvxCRAbkakpEd/CoviHLVe5Ei1ixWsYCm0SQV7cl/0Ugpep+GO4w3nJuwtR9jQbqdTs2VDbQoLAAeUwwNoGw1lmssJpAoGAesWAAjZleK1Zt51uCDBm3E7PZqEBbBoYxPqzrx1N6W94Tf4bfSL70VUKLq2iupyAaFviHPkJVlkDQuYt1YrSEGoslE4lFguXXUJZ66Hya63U5rlT+R9g4uM/tXZ8EEt6yho4FLLIIaIBfYrLDNQNMQJ4GKK4pA01V9mcMf9N/X4=/DLHZWUwAAeYoTWqGBmstFNaxkWjJX2nQD1mvepQq0PNhNiv18x27ygdLHC+n7bauvUJ4n8FrNIn2tcT62jYX/QU8Xj94Fr1vc06FInwqN4axN2Eff5rtLh0ed1P8uVZwfY+pm8cAIKN8a0u0AWD3gY6mBGk1s08grQK1L0pIA2pmUbhSUoL1IsDEhXZOpybDPiYEQxZyUrj7mJJTmzJh+neMpqku48nRDw2N86S8kYTn0wYyZhosQumr15NvC8vE3PIP4J0N5l3vQHcA4UcMFd4A/OPu1zbO7xjEfUIklkwIDAQAB";
    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAhKFl4Ao6mG2hOqOp+XXOTUnsWasr4/DOWWCQHYyapdpu5gEEPB0XavrQmwc70M4ry5gUq+hL3Cx1o7VGdpg9wHoZE27Zq27uERos4tJhlSD+HMBHpcM+2Gg5shteatvf4k7l4CKgs3RuXnCHUgUOs/wJggIVVjYRej0SCEy8AMbbFf3YGJ/6YagyJDFzIsTv+E8XUvKRks62Pew/qqoZGZT/zJ0RrkJmqDRv9iIjPYtQCPcym9nzsmDA4VTp2DKs53y5W4mM3oils6ZKuCksgG40iyChCfWYoJfsslGLRGg1dRs1dgonaaUREqpnrtC0E3tRH5HFr0dC7GqX3T/GrwIDAQAB";
    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://fywxpay.free.vipnps.vip/alipay/notifyApp";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    // 即支付成功之后，需要跳转到的页面，一般为网站的首页
    public static String return_url = "http://fywxpay.free.vipnps.vip/alipay/returnUrl";

    // 签名方式
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String charset = "utf-8";

    // 支付宝网关
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

    // 日志存储路径
    public static String log_path = "C:\\";


    /**
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
