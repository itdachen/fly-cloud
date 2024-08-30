import {defineConfig, loadEnv} from "vite";
import vue from "@vitejs/plugin-vue";
import AutoImport from "unplugin-auto-import/vite";
import Components from "@layui/unplugin-vue-components/vite";
import {LayuiVueResolver} from '@layui/unplugin-vue-components/resolvers'
import {resolve} from "path";

const excludeComponents = ['LightIcon', 'DarkIcon']

// export default defineConfig({

export default ({mode}) => defineConfig({

    define: {
        'process.env': {
            'VITE_BASE_URL': loadEnv(mode, process.cwd()).VITE_BASE_URL,
            'VITE_PROXY_URL': loadEnv(mode, process.cwd()).VITE_PROXY_URL
        }
    },


    resolve: {
        alias: [
            {
                find: '@',
                replacement: resolve(__dirname, './src')
            }
        ]
        // alias: {
        //     '@': resolve(__dirname, 'src')
        // }
    },


    // 配置ip
    server: {
        host: '0.0.0.0',
        port: Number(loadEnv(mode, process.cwd()).VITE_PORT),
        open: true,
        //     proxy: {
        //         // 如果是 /api 打头，则访问地址如下
        //         '/api': {
        //             target: loadEnv(mode, process.cwd()).VITE_PROXY_URL,
        //             changeOrigin: true,
        //             rewrite: (path) => path.replace(/^\/api/, '')
        //         }
        //     }
    },

    plugins: [
        AutoImport({
            resolvers: [
                LayuiVueResolver(),
            ],
        }),
        Components({
            resolvers: [
                LayuiVueResolver({
                    resolveIcons: true,
                    exclude: excludeComponents
                }),
            ],
        }),
        vue(),
    ],
});