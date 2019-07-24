import model.ImmutableQueue;
import model.Queue;
import org.junit.Assert;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ImmutableQueueTest {
    @Test
    public void testEnQueue(){
        // Prepare
        Queue<String> queue = new ImmutableQueue<>();

        // Process
        Queue<String> queueAfterEnqueue1 = queue.enQueue("1");
        Queue<String> queueAfterEnqueue2 = queueAfterEnqueue1.enQueue("2");

        // Assert
        Assert.assertNotEquals(queue, queueAfterEnqueue1);
        Assert.assertNotEquals(queueAfterEnqueue1, queueAfterEnqueue2);
        Assert.assertEquals("1", queueAfterEnqueue1.head());
        Assert.assertEquals("1", queueAfterEnqueue2.head());
        Assert.assertTrue(queue.isEmpty());
        Assert.assertFalse(queueAfterEnqueue1.isEmpty());
    }

    @Test
    public void testDeQueue(){
        // Prepare
        Queue<String> queue = new ImmutableQueue<>();
        Queue<String> queueAfterEnqueue1 = queue.enQueue("1");
        Queue<String> queueAfterEnqueue2 = queueAfterEnqueue1.enQueue("2");

        // Process
        Queue<String> queueAfterDequeue = queueAfterEnqueue2.deQueue();

        // Assert
        Assert.assertNotEquals(queueAfterEnqueue2, queueAfterDequeue);
        Assert.assertEquals("2", queueAfterDequeue.head());
        Assert.assertEquals("1", queueAfterEnqueue2.head());
    }

    @Test
    public void testException(){
        // Prepare
        Queue<String> queue = new ImmutableQueue<>();
        // Assert
        assertThatThrownBy(queue::head).isInstanceOf(IndexOutOfBoundsException.class);
        assertThatThrownBy(queue::deQueue).isInstanceOf(IndexOutOfBoundsException.class);
    }
}