package controllers;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import Main.Main;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
@Path("ability/")
public class AbilityController{
    @GET
    @Path("bonus/")
    @Produces(MediaType.APPLICATION_JSON)
    public String checkAbilityScores(){
        JSONObject bonusScore = new JSONObject();
        JSONArray bonus = new JSONArray();
        String[] abilities = {"STR","DEX","CON","INT","WIS","CHA"};
        int base = 0;
        int[] bases = new int[6];
        try{
            for(int x = 0; x < 6; x++){
                for(int y = 0; y < 6; y++) {
                    base = (int)((Math.random() * 6) + 1);
                    bases[y]=base;
                }
                Arrays.sort(bases);
                base = bases[5]+bases[4]+bases[3];
                PreparedStatement ps = Main.db.prepareStatement("SELECT bonus FROM abilityBonus WHERE base =?");
                ps.setInt(1,base);
                ResultSet rs = ps.executeQuery();
                JSONObject jso = new JSONObject();
                jso.put("ability",abilities[x]);
                jso.put("base",base);
                jso.put("bonus", rs.getInt(1));
                bonus.add(jso);
            }
            bonusScore.put("bonus",bonus);
            System.out.println(bonusScore.toString());
            return bonusScore.toString();
        }catch(Exception e){
            Console.log(e.getMessage());
            return "{'error': '" + e.getMessage() + "'}";
        }

    }
}
