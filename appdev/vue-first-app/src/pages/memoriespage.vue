<template>
  <base-layout page-title="All Memories">
    <memories-list :memories="memories"> </memories-list>
  </base-layout>
</template>

<script>
import { defineComponent } from "vue";
import MemoriesList from "../components/memories/MemoriesList.vue";

export default defineComponent({
  components: {
    MemoriesList,
  },
  computed: {
    memories() {
      return this.$store.getters.memories;
    },
  },
});
</script>
