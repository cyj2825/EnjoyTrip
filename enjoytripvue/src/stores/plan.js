import { ref } from "vue";
import { defineStore } from "pinia";

export const usePlanStore = defineStore("planStore", ()=>{
    const planTitle = ref('');
    const totalDays = ref('');
    const startDate = ref('');
    const endDate = ref('');

    return {
        planTitle,
        totalDays,
        startDate,
        endDate
    }
},
{persist: true})