package com.nich01as.uber2015;

public class Main2 {
	
	private static class UFile {
		int location = 0;
		private String content;
		UFile(String content) {
			this.content = content;
		}
		
		public int read(StringBuilder sb, int len) {
			if (location >= content.length()) {
				return 0;
			}
			int readLocation = location + len;
			if (readLocation > content.length()) {
				readLocation = content.length();
			}
			sb.append(content.substring(location,  readLocation));
			
			int readCount = readLocation - location;
			location = readLocation + 1;
			return readCount;
		}
		
		private String readContent = "";
		
		public int readLine(StringBuilder sb, int len) {
			if (len <= 0) {
				return 0;
			}
			StringBuilder lineSb = new StringBuilder(readContent);
			if (lineSb.length() < len) {
				read(lineSb, len - lineSb.length());
			}
			
			if (lineSb.length() ==0) {
				return 0;
			}
			int newLineIdx = -1;
			for (int i = 0; i < lineSb.length(); i++) {
				if (lineSb.charAt(i) == '\n') {
					newLineIdx = i;
					break;
				}
			}
			if (newLineIdx >= 0) {
				if (len <= newLineIdx+1) {
					sb.append(lineSb.substring(0, len));
					readContent = lineSb.substring(len);
					return len;
				} else {
					sb.append(lineSb.substring(0,  newLineIdx+1));
					readContent = lineSb.substring(newLineIdx+1);
					return newLineIdx+1;
				}
			} else {
				if (len < lineSb.length()) {
					sb.append(lineSb.substring(0, len));
					readContent = lineSb.substring(len);
					return len;
				} else {
					sb.append(lineSb);
					readContent = "";
					return lineSb.length() + readLine(sb, len - sb.length());
				}
			}
		}
	}
	
	public static void main(String[] args) {
		
		//System.out.println("abcd".substring(1, 2));
		UFile file = new UFile("aaaa\nbbbbb\nc");
		StringBuilder sb = new StringBuilder();
//		int count = file.read(sb, 3);
//		System.out.println(sb.toString());
//		
//		file = new UFile("aaaa\nbbbbb\nc");
//		count = file.read(sb, 4);
//		System.out.println(sb.toString() + "readCount " + count);
//		
//		file = new UFile("aaaa\nbbbbb\nc");
//		sb = new StringBuilder();
//		count = file.read(sb, 400);
//		System.out.println(sb.toString()+ "readCount " + count);
		
//		file = new UFile("aaaa\nbbbbb\nc");
//		sb = new StringBuilder();
//		int count = file.readLine(sb, 2);
//		System.out.println(sb.toString() + "readCount " + count);
//		
		file = new UFile("aaaa\nbbbbb\nc");
		sb = new StringBuilder();
		int count = file.readLine(sb, 1);
		System.out.println(sb.toString() + "\nreadCount " + count);
		
		sb = new StringBuilder();
		count = file.readLine(sb, 12);
		System.out.println(sb.toString() + "\nreadCount " + count);
		
		sb = new StringBuilder();
		count = file.readLine(sb, 102);
		System.out.println(sb.toString() + "\nreadCount " + count);
	}

}
