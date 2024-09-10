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
        let msg = '您将删除所有选中的数据？数据删除后将无法恢复！';
        if (!StringUtils.isEmpty(props.title)) {
            msg = '确定要删除【' + props.title + '】吗？数据删除后将无法恢复！';
        }
        layer.confirm(msg, {
            title: '系统提示',
            icon: 3,
            btn: [
                {
                    // style: 'background-color: #1e9fff; letter-spacing: 2px;border: 1px #1e9fff solid',
                    class: 'fly-button fly-ok-button',
                    text: '确定', callback: (layerId: string) => {
                        // @ts-ignore
                        props.callback();
                        layer.close(layerId);
                    }
                },
                {
                    class: 'fly-button fly-close-button',
                    text: '取消', callback: (layerId: string) => {
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