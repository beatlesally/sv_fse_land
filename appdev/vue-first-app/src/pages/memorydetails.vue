<template>
  <base-layout
v-if="loadedMemory"
    :page-title="loadedMemory.title"
    page-default-back-link="/memories"  >
    <h2 v-if="!loadedMemory">Could not find a memory for given id</h2>
    <h2 v-else>Loaded</h2>
  </base-layout>
</template>

<script>
import { defineComponent } from "vue";
import { IonList, IonItem } from "@ionic/vue";

export default defineComponent({
  components: {
    IonList,
    IonItem,
  },
  data() {
    return {
      memoryId: this.$route.params.id,
      loadedMemory: null,
    };
  },
  mounted() {
    console.log("Mounted function called");
    console.log("memoryId:", this.memoryId);
    this.loadedMemory = this.$store.getters.memory(this.memoryId);
    console.log("loadedMemory:", this.loadedMemory);
  },

  // watch: {
  //   $route(currentRoute) {
  //     this.memoryId = currentRoute.params.id;
  //   }
  // }
});
</script>

