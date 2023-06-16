<template>
    <base-layout page-title="loadedMemory ? loadedMemory.title : 'Loading...' " page-default-back-link="/memories">
        <h2 v-if="!loadedMemory">Could not find a memory for given id</h2>
        <h2 v-else>Loaded</h2>
    </base-layout>
</template>

<script>
import { defineComponent } from 'vue';
import { IonList, IonItem } from '@ionic/vue';

export default defineComponent({
  components: {
    IonList,
    IonItem
  },
  data () {
    return {
      memoryId: this.$route.params.id
    }
  },
  computed: {
    loadedMemory() {
      return this.$store.getters.memory(this.memoryId);
    },
  },
  watch: {
    '$route'(currentRoute) {
      this.memoryId = currentRoute.params.id;
    }
  }
});
</script>

