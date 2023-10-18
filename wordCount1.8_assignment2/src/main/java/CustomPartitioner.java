import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

public class CustomPartitioner extends Partitioner<Text, IntWritable> {

    /*
    Use the character m as base and mod with the number of reduce tasks to make it properly partitioned
     */
    @Override
    public int getPartition(Text key, IntWritable value, int numReduceTasks) {
        char modFactor = 'm';

        if (numReduceTasks == 0) return 0;

        char firstCharacter = key.toString().charAt(0);

        return (Character.toLowerCase(firstCharacter) - modFactor) % numReduceTasks;
    }
}
