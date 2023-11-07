package cn.tulingxueyuan.beans;

import org.springframework.context.annotation.DeferredImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

/***
 * @Author 徐庶   QQ:1092002729
 * @Slogan 致敬大师，致敬未来的你
 *
 * 延迟 特性，  分组特性
 */
public class MyDeferredImportSelector implements DeferredImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        System.out.println("MyDeferredImportSelector.selectImports");
        return new String[]{"cn.tulingxueyuan.beans.Person"};
    }

   /* @Override
    public Predicate<String> getExclusionFilter() {
        return null;
    }*/


    @Override
    public Class<? extends Group> getImportGroup() {
        // 这个返回值决定调用DeferredImportSelector.selectImports  如果null
        // 还是调用Group.selectImports
        //return MyGroup.class;
        return null;
    }

    // 如果getImportGroup返回自定义Group ， 会调用自定义Group的process方法
    // 如果getImportGroup返回 null,会调用DefaultDeferredImportSelectorGroup的process方法,即调用selectImports
    // 分组利用归类，同一组的bean只影响本组的顺序
    private static class MyGroup
            implements Group {

        AnnotationMetadata metadata;

        @Override
        public void process(AnnotationMetadata metadata, DeferredImportSelector selector) {
            this.metadata = metadata;
        }

        @Override
        public Iterable<Entry> selectImports() {
            System.out.println("MyGroup.selectImports");
            List<Entry> list = new ArrayList<>();
            list.add(new Entry(this.metadata, "cn.tulingxueyuan.beans.Person"));
            return list;
        }
    }


}
