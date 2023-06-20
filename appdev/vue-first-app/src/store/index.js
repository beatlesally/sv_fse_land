import { createStore } from 'vuex';

const store = createStore({
    state() {
        return {
            memories: [
                {
                  id: "m1",
                  image:
                    "https://images.unsplash.com/photo-1545569341-9eb8b30979d9?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=870&q=80",
                  title: "Japan",
                  description: "It was nice!",
                },
                {
                  id: "m2",
                  image:
                    "https://images.unsplash.com/photo-1683009686716-ac2096a5a73b?ixlib=rb-4.0.3&ixid=M3wxMjA3fDF8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=870&q=80",
                  title: "Hiking",
                  description: "It was nice hiking!",
                },
                {
                  id: "m3",
                  image:
                    "https://images.unsplash.com/photo-1470376619031-a6791e534bf0?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=870&q=80",
                  title: "Swimming",
                  description: "It was nice swimming!",
                },
              ],
        }; 
    },
    getters: {
        memories(state) {
            return state.memories;
        },
        memory: (state) => (memoryId) => {
                return state.memories.find((memory) => memory.id === memoryId);
            },
        
    }
})
export default store;