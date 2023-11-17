import { ref } from "vue";
import { defineStore } from "pinia";

export const usePlanStore = defineStore("planStore", ()=>{
    const planTitle = ref('');
    const totalDays = ref('');

    return {
        planTitle,
        totalDays
    }
})