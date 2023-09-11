package application.android.com.rushtechnologies.spaceride.Service.Volley;

public class ServiceProperties {
    public String url = "http://ip/applications/Android_Migration.php?caso=casos&param1=par1&param2=par2&param3=par3&param4=par4&param5=par5&param6=par6&param7=par7&param8=par8&param9=par9";
    //public String url = "http://ip/aplicaciones/Android_Migration.php?caso=casos&param1=par1&param2=par2&param3=par3&param4=par4&param5=par5&param6=par6&param7=par7&param8=par8&param9=par9";
    //public final static String ip = "192.168.15.8";
    //public final static String ip = "192.168.30.101";
    //public final static String ip = "192.168.30.116";
    //public final static String ip = "192.168.30.104";
    public final static String ip = "192.168.137.1";

    public String setUrl(String casos , String par1, String par2, String par3, String par4, String par5, String par6, String par7, String par8, String par9) {
        url = url.replace("ip", ip);
        url = url.replace("casos", casos);
        url = url.replace("par1", par1);
        url = url.replace("par2", par2);
        url = url.replace("par3", par3);
        url = url.replace("par4", par4);
        url = url.replace("par5", par5);
        url = url.replace("par6", par6);
        url = url.replace("par7", par7);
        url = url.replace("par8", par8);
        url = url.replace("par9", par9);
        url = url.replace(" ", "%20");
        return url;
    }

}
