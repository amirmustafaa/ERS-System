import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import dao.ReimbursementDaoImpl;



public class ProjectTest {
	ReimbursementDaoImpl dao = new ReimbursementDaoImpl();
	
	@Test
	public void loginTest() {
		assertEquals(null, dao.login("testtest", "testing"));
		
	}

}
