package org.work;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;
import org.mindjet.Parser;
import org.mindjet.Topic;


public class TestCubeTopic {

	public static void toMarkDwon(OutputStreamWriter out, Topic topic) throws IOException {
		String id = topic.path.trim();

		if (topic == null) {
			return;
		}
		if(topic.parent == null){	// root element
			out.append("```\n" + "graph LR\n");
			if (!topic.isLeaf()) {
				List<Topic> children = topic.getChildren();
				for (Topic child : children){
					String childName = child.text;
					String childId =  child.path.trim();
					out.append(String.format("%s-->%s(%s)\n", id, childId, childName));
					toMarkDwon(out,child);
				}
			}
			out.append("```\n");
		}else {
			// descendant element
			if (!topic.isLeaf()) {
				List<Topic> children = topic.getChildren();
				for (Topic child : children) {
					String childName = child.text;
					String childId = child.path.trim();
					out.append(String.format("%s-->%s(%s)\n", id, childId, childName));
					toMarkDwon(out, child);
				}
			}
		}

	}
	public static void main(String[] arg) throws IOException {
		Parser p = new Parser("C:\\Users\\robertpicyu\\Desktop\\tmp\\test.mmap");
		
		org.work.CubeTopic c = new org.work.CubeTopic();
		//c.parse(p.root);
		OutputStreamWriter buffer = new OutputStreamWriter(System.out);
		toMarkDwon(buffer,p.root);
		buffer.flush();
		buffer.close();
	}
}
