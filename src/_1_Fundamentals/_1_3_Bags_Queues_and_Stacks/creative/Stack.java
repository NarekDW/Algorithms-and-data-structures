package _1_Fundamentals._1_3_Bags_Queues_and_Stacks.creative;

interface Stack<T> {
    void push(T t);

    T pop();

    boolean isEmpty();

    int size();
}

interface Queue<T> {
    void enqueue(T t);

    T dequeue();

    boolean isEmpty();

    int size();
}
