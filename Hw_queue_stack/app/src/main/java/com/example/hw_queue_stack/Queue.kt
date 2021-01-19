package com.example.hw_queue_stack

import java.util.Stack

class Queue(){

    /** Initialize your data structure here. */

    var s1: Stack<Int> = Stack()
    var s2: Stack<Int> = Stack()
    /** Push element x to the back of queue. */
    fun push(x: Int) {
        s1.push(x)
    }

    /** Removes the element from in front of queue and returns that element. */
    fun pop(): Int {
        if(s2.empty()){
            while(!s1.empty()){
                s2.push(s1.peek())
                s1.pop()
            }
        }
        val y = s2.peek()
        s2.pop()
        return y;
    }

    /** Get the front element. */
    fun peek(): Int {
        if(s2.empty()){
            while(!s1.empty()){
                s2.push(s1.peek())
                s1.pop()
            }
        }
        return s2.peek();
    }

    /** Returns whether the queue is empty. */
    fun empty(): Boolean {
        return s2.empty() && s1.empty()
    }

}

/**
 * Your MyQueue object will be instantiated and called as such:
 * var obj = MyQueue()
 * obj.push(x)
 * var param_2 = obj.pop()
 * var param_3 = obj.peek()
 * var param_4 = obj.empty()
 */