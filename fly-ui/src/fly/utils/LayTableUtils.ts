import {layer} from "@layui/layui-vue";
import {StringUtils} from "@/fly/utils/StringUtils";

/**
 * 表格操作
 */
export class LayTableUtils {

    /**
     * 删除
     * @param props
     */
    static remove(props: RemoveLayProps) {
        let msg = '您将删除所有选中的数据？';
        if (!StringUtils.isEmpty(props.title)) {
            msg = '确定要删除【' + props.title + '】吗';
        }
        layer.confirm(msg, {
            title: '系统提示',
            icon: 3,
            btn: [
                {
                    text: '确定', callback: (layerId: any) => {
                        props.callback;
                        layer.close(layerId);
                    }
                },
                {
                    text: '取消', callback: (layerId: any) => {
                        layer.close(layerId)
                    }
                }
            ]
        })
    }

}


export interface RemoveLayProps {
    title?: string,
    callback?: Function
}