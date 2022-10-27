import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.given;
public class ParseJson {

    public static void main(String[] args) {

        //Getting response in String
        RestAssured.baseURI = "https://samples.openweathermap.org";

       String response = given().queryParam("q","London,U%20K").queryParam("appid","b1b15e%2088fa797225412429c1c50c122a1")
                .when().get("data/2.5/history/city")
                .then().extract().response().asString();


        //Retrieving response content using JsonPath
        JsonPath js = new JsonPath(response);
        String message = js.getString("message");
        String responseCode = js.getString("cod");
        String city_id = js.getString("city_id");
        String calcTime = js.getString("calctime");
        //printing above all values

        System.out.println("Message is " + message);
        System.out.println("Response Code is " + responseCode);
        System.out.println("City ID is " + city_id);
        System.out.println("CalcTime is " + calcTime);

        //getting size of list array
        int list = js.getInt("list.size()");


        //Iterating through list array and printing all the values
        for (int i = 0; i<list; i++)
        {
            System.out.println("----------------------------------------------------------------------------------");
            System.out.println("Temperature is " + js.getString("list["+i+"].main.temp"));
            System.out.println("Min Temperature is " + js.getString("list["+i+"].main.temp_min"));
            System.out.println("Max Temperature is " + js.getString("list["+i+"].main.temp_max"));
            System.out.println("Pressure is " + js.getString("list["+i+"].main.pressure"));
            System.out.println("Sea Level is " + js.getString("list["+i+"].main.sea_level"));
            System.out.println("Ground Level  is " + js.getString("list["+i+"].main.grnd_level"));
            System.out.println("Humidity is " + js.getString("list["+i+"].main.humidity"));
            System.out.println("--------------Finished Main Component for " +i+ " list array----------------------");

            System.out.println("Speed of wind is " + js.getString("list["+i+"].wind.speed"));
            System.out.println("Deg of wind is " + js.getString("list["+i+"].wind.deg"));
            System.out.println("--------------Finished Wind Component for " +i+ " list array----------------------");

            System.out.println("Value of all attribute is " + js.getString("list["+i+"].clouds.all"));
            System.out.println("--------------Finished Clouds Component for " +i+ " list array----------------------");

            System.out.println("Value of all attribute is " + js.getString("list["+i+"].rain.3h"));
            System.out.println("--------------Finished rain Component for " +i+ " list array----------------------");


            //counting size of weather array
            int weather = js.getInt("list["+i+"].weather.size()");
            //Iterating through weather array and printing values
            for(int j =0; j<weather; j++)
            {
                System.out.println("Weather ID  is " + js.getString("list["+i+"].weather["+j+"].id"));
                System.out.println("Main attribute for weather is " + js.getString("list["+i+"].weather["+j+"].main"));
                System.out.println("Description is " + js.getString("list["+i+"].weather["+j+"].description"));
                System.out.println("Icon value is  is " + js.getString("list["+i+"].weather["+j+"].icon"));
            }
            System.out.println("--------------Finished weather Component for " +i+ " list array----------------------");
            System.out.println("Value of dt is "+ js.getString("list["+i+"].dt"));




        }


    }
}
