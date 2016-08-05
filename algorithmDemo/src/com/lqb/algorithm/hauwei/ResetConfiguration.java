package com.lqb.algorithm.hauwei;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 为了简化输入，方便用户，以“最短唯一匹配原则”匹配：
 * 1、若只输入一字串，则只匹配一个关键字的命令行。例如输入：r，根据该规则，匹配命令reset，执行结果为：reset what；输入：res，根据该规则，匹配命令reset，执行结果为：reset what；
 * 2、若只输入一字串，但本条命令有两个关键字，则匹配失败。例如输入：reb，可以找到命令reboot backpalne，但是该命令有两个关键词，所有匹配失败，执行结果为：unkown command
 * 3、若输入两字串，则先匹配第一关键字，如果有匹配但不唯一，继续匹配第二关键字，如果仍不唯一，匹配失败。例如输入：r b，找到匹配命令reset board，执行结果为：board fault。
 * 4、若输入两字串，则先匹配第一关键字，如果有匹配但不唯一，继续匹配第二关键字，如果唯一，匹配成功。例如输入：b a，无法确定是命令board add还是backplane abort，匹配失败。
 * 5、若输入两字串，第一关键字匹配成功，则匹配第二关键字，若无匹配，失败。例如输入：bo a，确定是命令board add，匹配成功。
 * 6、若匹配失败，打印“unkown command”
 * @Author:JackBauer
 * @Date:2016年8月5日
 */
public class ResetConfiguration {

	Map<String, String> commands = new HashMap<>();

	public ResetConfiguration() {
		commands.put("reset", "reset what");
		commands.put("reset board", "board fault");
		commands.put("board add", "where to add");
		commands.put("board delet", "no board at all");
		commands.put("reboot backplane", "impossible");
		commands.put("backplane abort", "install first");
	}

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		ResetConfiguration test = new ResetConfiguration();
		while (input.hasNext()) {
			String command = input.nextLine();
			String key = null;
			if (test.isOneKeyCommand(command)) {
				key = test.matchOneKeyCommand(command);
			} else {
				key = test.matchDoubleKeyCommand(command);
			}

			if (key == null) {
				System.out.println("unknown command");
			} else {
				System.out.println(test.getValue(key));
			}
		}
		input.close();
	}

	private String matchOneKeyCommand(String inputKey) {
		String outputKey = null;
		for (String key : commands.keySet()) {
			if (key.startsWith(inputKey) && key.split(" ").length == 1) {
				if (outputKey == null) {
					outputKey = key;
				} else if(outputKey != null){
					return null;
				}
			}
		}
		return outputKey;
	}

	private String matchDoubleKeyCommand(String inputCommand) {
		String[] inputCommandParts = inputCommand.split(" ");
		String outputKey = null;
		for(String key : commands.keySet()){
			String[] commandParts = key.split(" ");
			if(commandParts.length > 1 && commandParts[0].startsWith(inputCommandParts[0]) && commandParts[1].startsWith(inputCommandParts[1])){
				if(outputKey == null){
					outputKey = key;
				}else{
					return null;
				}
			}
		}
		return outputKey;
	}

	private String getValue(String key) {
		return commands.get(key);
	}

	private boolean isOneKeyCommand(String command) {
		String[] commandParts = command.split(" ");
		return commandParts.length == 1;
	}
}
