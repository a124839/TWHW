package twHomeWork;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class TWHomeWork {

	
	public static void main(String[] args) {
		//读取的文本文件路径按照"D:\\newFile\\test.txt"方式
		//或者"D:/newFile/test.txt"给定，
		//否则java报错，认为windows中的"\"为转义字符而不是字符串
		
		String fPath = "E:\\TWHomeWorkData.txt";
		TWHomeWork tw = new TWHomeWork();
		ArrayList<String> list = new ArrayList<>();
		list = tw.readTextFile(fPath);
		tw.isFine(list, 0);
	}
	
	/**
	 * 2018年3月15日下午9:25:46-cl
	 * 读取文件，并返回list
	 * @param filePath
	 * @return list
	 */
	public ArrayList<String> readTextFile(String filePath) {
		ArrayList<String> list = new ArrayList<>();
		try {
			String encoding = "GBK";
			File file = new File(filePath);
			if (file.isFile() && file.exists()) { // 判断文件是否存在
				InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);// 考虑到编码格式
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;
				while ((lineTxt = bufferedReader.readLine()) != null) {
					list.add(lineTxt);
				}
				read.close();
				return list;
			} else {
				System.out.println("找不到指定的文件");
			}
		} catch (Exception e) {
			System.out.println("读取文件内容出错");
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 2018年3月15日下午9:25:00-cl
	 * 判断无人机是否故障
	 * @param list
	 * @param index
	 */
	public void isFine(ArrayList<String> list, int index) {
		if (list == null) {
			System.err.println("未能成功获取文件内容");
			return;
		}
		//如果index大于文本行数
		if (list.size() < index) {
			System.out.println("Cannot find " + index);
			return;
		}
		if (index == 0) {
			String[] ss = list.get(0).split(" ");
			System.out.println(ss[0] + " " + 
					index +" "+ ss[1] + " "+ ss[2] + " " + ss[3]);
			return;
		}
		int count = 0;
		ArrayList<Integer> locations = new ArrayList<>();//用来存放位置
		while (count <= index) {
			String tmp = list.get(count);//拿到一行数据
			String[] tmps = tmp.split(" ");//分割字符串，得到id与位置
			ArrayList<Integer> tmpList = new ArrayList<>();//存储位置
			//拿到位置数据
			for (int i = 1; i < tmps.length; i++) {
				tmpList.add(Integer.valueOf(tmps[i]));
			}
			//如果第0行不满足条件直接跳出
			if (count == 0 && tmpList.size() != 3) {
				System.out.println("Error: " + index);
				break;
			}
			//如果没有offset的位置直接跳出
			if (count != 0 && tmpList.size() != 6) {
				System.out.println("Error: " + index);
				break;
			}
			if (count == 0 ) {
				locations = tmpList;
			}else {
				if (!isSameLocation(locations, tmpList)) {
					System.out.println("Error: " + index);
					break;
				}
				if (count == index && isSameLocation(locations, tmpList)) {
					locations = getLocation(tmpList);
					System.out.println(tmps[0] + " "+ index + " " + 
							locations.get(0) + " " + locations.get(1) + " " 
								+ locations.get(2));
					break;
				}
				locations = getLocation(tmpList);
			}
			count++;
		}
	}

	/**
	 * 2018年3月15日下午9:07:03-cl
	 * 判断位置是否相等
	 * @param locations
	 * @param tmpList
	 * @return
	 */
	private boolean isSameLocation(ArrayList<Integer> locations, ArrayList<Integer> tmpList) {
		// TODO Auto-generated method stub
		for (int i = 0; i < 3; i++) {
			if (locations.get(i) != tmpList.get(i)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 2018年3月15日下午9:03:55-cl
	 * 计算新的位置信息
	 * @param tmpList
	 * @return 新的位置信息
	 */
	private ArrayList<Integer> getLocation(ArrayList<Integer> tmpList) {
		// TODO Auto-generated method stub
		if (tmpList.size() == 3) {
			return tmpList;
		}else {
			ArrayList<Integer> location = new ArrayList<>();
			for (int i = 0; i < 3; i++) {
				location.add(tmpList.get(i)+tmpList.get(i+3));
			}
			return location;
		}
	}
	
	

}
