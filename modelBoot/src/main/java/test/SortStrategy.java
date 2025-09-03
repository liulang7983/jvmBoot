package test;

/**
 * 策略模式
 * 感觉像接口的不同实现，只是可以自己手动的却设置策略
 * @Author ming.li
 * @Date 2025/9/3 15:02
 * @Version 1.0
 */
// 排序策略接口
interface SortStrategy {
    void sort(int[] array);
}

// 冒泡排序策略
class BubbleSortStrategy implements SortStrategy {
    @Override
    public void sort(int[] array) {
        System.out.println("冒泡Sorting using Bubble Sort");
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    // 交换元素
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }
}

// 快速排序策略
class QuickSortStrategy implements SortStrategy {
    @Override
    public void sort(int[] array) {
        System.out.println("快速Sorting using Quick Sort");
        quickSort(array, 0, array.length - 1);
    }

    private void quickSort(int[] array, int low, int high) {
        if (low < high) {
            int pi = partition(array, low, high);
            quickSort(array, low, pi - 1);
            quickSort(array, pi + 1, high);
        }
    }

    private int partition(int[] array, int low, int high) {
        int pivot = array[high];
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (array[j] <= pivot) {
                i++;
                // 交换元素
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }
        // 交换pivot
        int temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;
        return i + 1;
    }
}

// 排序上下文
class Sorter {
    private SortStrategy strategy;

    // 设置排序策略
    public void setStrategy(SortStrategy strategy) {
        this.strategy = strategy;
    }

    // 执行排序
    public void sortArray(int[] array) {
        strategy.sort(array);
        // 打印排序结果
        printArray(array);
    }

    private void printArray(int[] array) {
        for (int num : array) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}

// 使用示例
class StrategyDemo {
    public static void main(String[] args) {
        int[] array = {5, 2, 9, 1, 5, 6};

        Sorter sorter = new Sorter();

        // 使用冒泡排序
        sorter.setStrategy(new BubbleSortStrategy());
        sorter.sortArray(array.clone());

        // 切换为快速排序
        sorter.setStrategy(new QuickSortStrategy());
        sorter.sortArray(array.clone());
    }
}

