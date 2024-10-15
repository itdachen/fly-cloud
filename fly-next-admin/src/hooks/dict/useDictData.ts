import {ref, reactive} from "vue";
import {DictData} from "/@/hooks/biz/BizModel";


/**
 * 常用的数据字典
 */
export default function useDictDataComposable() {

    const Y = ref<string>('Y');
    const N = ref<string>('N');

    /**
     * 有效标志
     */
    const validFlag = reactive<DictData[]>([
        {
            label: '是',
            value: Y,
            checked: Y.value
        },
        {
            label: '否',
            value: N.value,
            checked: N.value
        }
    ])

    /**
     * 是与否
     */
    const yesOrNo = validFlag;


    /**
     * 性别
     */
    const sexDict = reactive<DictData[]>([
        {
            label: '男',
            value: 'M',
            checked: Y.value
        },
        {
            label: '女',
            value: 'W',
            checked: N.value
        },
        {
            label: '未知',
            value: 'UN',
            checked: N.value
        }
    ]);

    return {
        // Y, N,
        validFlag,
        yesOrNo,
        sexDict
    }

}