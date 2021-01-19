package com.example.hw_queue_stack

import java.util.*
import java.util.Queue

class Stack() {

    /** Initialize your data structure here. */
    var q1: Queue<Int> = LinkedList<Int>();
    var q2: Queue<Int> = LinkedList<Int>();
    var size = 0;

    /** Push element x onto stack. */
    fun push(x: Int) {
        size++;
        q1.add(x);
        while(!q2.isEmpty()){
            q1.add(q2.peek())
            q2.poll()
        }
        val q: Queue<Int> = q2
        q2 = q1
        q1 = q
//        while(!q2.isEmpty()){
//            q1.add(q2.peek())
//        }
    }

    /** Removes the element on top of the stack and returns that element. */
    fun pop(): Int {
        size--;
        return if(q2 == null)
            0
        else
            q2.poll()
    }

    /** Get the top element. */
    fun top(): Int {
        return if(q2 == null)
            0
        else
            q2.peek()
    }

    /** Returns whether the stack is empty. */
    fun empty(): Boolean {
        return size == 0
    }

}

/**
 * Your MyStack object will be instantiated and called as such:
 * var obj = MyStack()
 * obj.push(x)
 * var param_2 = obj.pop()
 * var param_3 = obj.top()
 * var param_4 = obj.empty()
 */