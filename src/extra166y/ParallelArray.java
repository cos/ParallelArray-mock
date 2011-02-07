package extra166y;

import java.util.HashSet;

public class ParallelArray<T> {
	public static final CharSequence OP_STRING = "op(";
	HashSet<T> pseudoArrayWithin = new HashSet<T>();
	
	public static jsr166y.ForkJoinPool defaultExecutor() {
		return null;
	}
	public static <T> ParallelArray<T> create(int size, Class<? super T> elementType, jsr166y.ForkJoinPool executor)
	{
		return new ParallelArray<T>();
	}
	
	public static <T> ParallelArray<T> createUsingHandoff(T[] source, jsr166y.ForkJoinPool executor)
	{
		ParallelArray<T> pa = new ParallelArray<T>();
		pa.pseudoArrayWithin.add(source[0]);
		return pa;
	}
	
	public void apply(Ops.Procedure<? super T> procedure) 
	{
		procedure.op(pseudoArrayWithin.iterator().next());
	}
	
	public void replaceWithGeneratedValue(Ops.Generator<? super T> generator) 
	{
		pseudoArrayWithin.add((T) generator.op());
	}
}
