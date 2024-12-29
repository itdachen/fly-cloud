import { defineStore } from 'pinia'

export const useUserStore = defineStore({
    id: 'dict',
    state: () => {
        return { }
    },
    actions: {

    },
    persist: {
        storage: localStorage,
        paths: ['token', 'userInfo', 'permissions', 'menus' ],
    }
})