<template>
    <ion-list>
        <memory-list-item v-for="memory in memories" :key="memory.id" :memory="memory"></memory-list-item>
    </ion-list>
</template>
<script>
import { defineComponent } from "vue";
import { IonList } from "@ionic/vue";
import MemoryListItem from './MemoriesListItem.vue'

export default defineComponent({
  components: {
    IonList,
    MemoryListItem
  },
  props: ['memories'],
})
</script>