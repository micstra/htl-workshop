import {fileURLToPath, URL} from 'url'

import {defineConfig} from 'vite'
import vue from '@vitejs/plugin-vue'

// https://vitejs.dev/config/
export default defineConfig({
    plugins: [vue()],
    server: {
        proxy: {
            "/backend": {
                target: "http://localhost:8080/",
                rewrite: path => path.replace("/backend", "")
            }
        }
    },
    resolve: {
        alias: {
            '@': fileURLToPath(new URL('./src', import.meta.url)),
            'workshop-client': fileURLToPath(new URL('./target', import.meta.url))
        }
    }
})
