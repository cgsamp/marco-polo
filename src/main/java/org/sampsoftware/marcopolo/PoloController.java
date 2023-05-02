package org.sampsoftware.marcopolo;

import java.util.Random;

import org.sampsoftware.regressions.MultipleLinearRegression;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;


@RestController
public class PoloController {

	@Autowired
    private JdbcTemplate jdbcTemplate;
     


	@GetMapping("/marco")
	public String marco() {
		String nodeName = System.getenv("MY_NODE_NAME");
		if (nodeName == null) { nodeName = "PLACEHOLDER-PLACE";}
		String podName = System.getenv("MY_POD_NAME");
		if (podName == null) { podName = "PLACEHOLDER-PLACE";}

		Random rand = new Random();
		int n = rand.nextInt(100);
		String polo = "";
		if (n < 20) {
			MultipleLinearRegression mlr = new MultipleLinearRegression();
			mlr.doSomeRegressionPlease();
			polo = "Regressed";
		} else if (n == 99){
			throw new RuntimeException("This is a gratuitous exception.");
		} else if (n < 22) {
			polo = insertPolo("podName","I was at "+nodeName);
		} else if (n < 35) {
			polo = countPolos();
		}



		String reply = "POLO -- TIME "
		+ java.time.LocalDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern("HH:mm:ss.SSS"))
		+ "   NODE-POD   " 
		+ String.format(nodeName.substring(nodeName.length()-5).toUpperCase())
		+ "-"
		+ String.format(podName.substring(podName.length()-5).toUpperCase())
		+ " " + polo
		;
		return reply;
	}

	private String insertPolo(String name, String description) {
		String sql = "INSERT INTO polo (name, description) VALUES ('"
		+ name 
		+ "','"
		+ description
		+ "')"
		;
 
		int rows = jdbcTemplate.update(sql);

		return "Inserted "+ rows + " row.";
	}

	private String countPolos() {
		String sql = "SELECT count(*) from polo";

		SqlRowSet rs = jdbcTemplate.queryForRowSet(sql);
		rs.next();
		int count = rs.getInt(1);
		return "There are "+ Integer.toString(count) + " rows.";
	}


}
