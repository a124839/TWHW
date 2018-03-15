package twHomeWork;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class TWHomeWorkTest {

	TWHomeWork tw = new TWHomeWork();
	
	@Test
	public void testReadTextFile() {
		String fPath = "E:\\【【找工作】】\\TWHomeWorkData.txt";
		ArrayList<String> list = new ArrayList<>();
		list = tw.readTextFile(fPath);
		System.out.println(list.toString());
		
	}

	@Test
	public void testIsFine() {
		String fPath = "E:\\TWHomeWorkData.txt";
		ArrayList<String> list = new ArrayList<>();
		list = tw.readTextFile(fPath);
		tw.isFine(list, 0);
	}

}
