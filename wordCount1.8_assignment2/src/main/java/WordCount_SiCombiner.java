import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class WordCount_SiCombiner {

    public static void main(String[] args) throws Exception {

        final int numReduceTasks = 5;

        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf, "word count");
        job.setJarByClass(WordCount_SiCombiner.class);

        job.setMapperClass(OriginalMapper.class);
//        job.setMapperClass(PerMapTallyMapper.class);
//        job.setMapperClass(PerTaskTallyMapper.class);

        // Activate the identical Combiner from Hadoop Distribution
        job.setCombinerClass(IntSumReducer.class);

        job.setReducerClass(IntSumReducer.class);

        // Custom partitioner
        job.setPartitionerClass(CustomPartitioner.class);

        job.setNumReduceTasks(numReduceTasks);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));
        System.exit(job.waitForCompletion(true) ? 0 : 1);
        System.gc();
    }
}