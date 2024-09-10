package rpg;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class FileOperation {
	String propertiesFilePath = "c:\\config\\game.properties";
	
	
	public int[] getHeroProperties() {
		int[] heroData = new int[5];
		
		try (FileReader fr = new FileReader(propertiesFilePath);){
			Properties p = new Properties();
			p.load(fr);
			
			System.out.println(p.getProperty("hero_hp"));
			
			heroData[0] = Integer.parseInt(p.getProperty("hero_hp"));
			heroData[1] = Integer.parseInt(p.getProperty("hero_mp"));
			heroData[2] = Integer.parseInt(p.getProperty("hero_power"));
			heroData[3] = Integer.parseInt(p.getProperty("hero_defenc"
					+ "e"));
			heroData[4] = Integer.parseInt(p.getProperty("hero_special_power"));
			
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			return heroData;
		}
	}
	
	public int[] rate() {
		int[] rate = new int[2];
		
		try (FileReader fr = new FileReader(propertiesFilePath);){
			Properties p = new Properties();
			p.load(fr);
			
			System.out.println(p.getProperty("hero_hp"));
			
			rate[0] = Integer.parseInt(p.getProperty("attack_rate"));
			rate[1] = Integer.parseInt(p.getProperty("special_attack_rate"));	
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			return rate;
		}
	}

	public List<String> readCsv(String csvFilePath) {
		List<String> csvData = new ArrayList<String>();
		
		try (FileReader fr = new FileReader(csvFilePath);
				BufferedReader br = new BufferedReader(fr);){
			
			String line = br.readLine();
			while((line = br.readLine()) != null) {
				csvData.add(line);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			return csvData;
		}
	}

}
