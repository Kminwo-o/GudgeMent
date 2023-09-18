import { createNativeStackNavigator } from "@react-navigation/native-stack";

import Play from "../pages/Play";
import { CommonType } from "../types/CommonType";
import PlaySelect from "../pages/PlaySelect";

function PlayNavigator() {
  const Stack = createNativeStackNavigator<CommonType.RootStackParamList>();
  return (
    <Stack.Navigator
      initialRouteName="Play"
      screenOptions={{ headerShown: false, animation: "none" }} // 전환 효과 사용 안 함
    >
      <Stack.Screen
        name="Play"
        component={Play}
        options={{ headerShown: false }}
      />
      <Stack.Screen
        name="PlaySelect"
        component={PlaySelect}
        options={{ headerShown: false }}
      />
    </Stack.Navigator>
  );
}
export default PlayNavigator;
