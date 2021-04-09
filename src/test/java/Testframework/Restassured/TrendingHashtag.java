package Testframework.Restassured;
import static io.restassured.RestAssured.given;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
public class TrendingHashtag extends base {
        String ConsumerKey ="ZeUlFSujysCG3RTeXefleFsuP";
            String    ConsumerSecret ="OAcWyG0Q7Y9BwcpmefRkG7jFiLRf0GJ277rRECumu83rNfBiC8";
            String    AccessToken ="1364824717895471104-Oy73iuTgbjd5jNDCh6cmOKvfgZtl2c";
                String TokenSecret ="wH4EIP7w9NTUz9ukJM18f7jYmc5j4w0rColk6MlAxs2Fy";
    Integer statusCode=200;
    Properties prop=new Properties();
    private static Logger log=LogManager.getLogger(TrendingHashtag.class.getName());
    
    @Test(dataProvider="locations")
    public void trending_HashTag(String loc,String geocode) throws IOException {
        RestAssured.baseURI="https://api.twitter.com/1.1/trends/";
        Response res=given().auth().oauth(ConsumerKey,ConsumerSecret,AccessToken,TokenSecret).
        queryParam("id",geocode).when().get("place.json").then().extract().response();
        String response=res.asString();
        JsonPath jp=new JsonPath(response);
        ArrayList<String> arr=new ArrayList<String>();
        String s=null;
        arr.addAll((Collection<? extends String>) jp.get("trends.name"));
        Iterator itr=arr.iterator();
        while(itr.hasNext())
        {
        s=itr.next().toString();
        }
    
        s=s.substring(1,s.length()-1);
        String result[]=s.split(",");
        System.out.println("-------------------"+loc+"-----------------------");
        for(int i=0;i<5;i++)
        {
            System.out.println(result[i]);
        }
        
    }
    @DataProvider
    public Object[][] locations(){
        Object loc[][]=new Object[4][2];
        loc[0][0]="india";
        loc[0][1] ="23424848";
        loc[1][0]="US";
        loc[1][1]="23424977";
        loc[2][0]="UK";
        loc[2][1]="23424975";
        loc[3][0]="Israel";
        loc[3][1]="23424852";
        return loc;
    }
    @AfterTest
    public void print() {
        if(statusCode==200) {
            log.info("Indian trends are displayed");
            log.info("trends in us:");
            log.info("trends in uk:");
            log.info("trends in israel:");
        }
    }

 

}