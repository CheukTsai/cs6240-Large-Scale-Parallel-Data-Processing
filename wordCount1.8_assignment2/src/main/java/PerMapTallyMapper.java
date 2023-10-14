import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class PerMapTallyMapper extends Mapper<Object, Text, Text, IntWritable> {

    private Text word = new Text();

    public void map(Object key, Text value, Context context ) throws IOException, InterruptedException {
        StringTokenizer itr = new StringTokenizer(value.toString());
        Map<String, Integer> cntMap = new HashMap<>();

        while (itr.hasMoreTokens()) {
            String curWord = itr.nextToken();

            if (Utils.checkStartCharacter(curWord, Utils.limitedCharacters)) {
                cntMap.put(curWord, cntMap.getOrDefault(curWord, 0) + 1);
            }
        }

        for (Map.Entry<String, Integer> entry : cntMap.entrySet()) {
            word.set(entry.getKey());
            context.write(word, new IntWritable(entry.getValue()));
        }
    }
}
