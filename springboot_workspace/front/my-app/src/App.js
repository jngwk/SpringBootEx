import React, { useState } from "react";
import Counter from "./hooks/Counter";
import Info from "./hooks/Info";
import ContextSample from "./hooks/ContextSample";
import Average from "./hooks/Average";

// const App = () => {
//   const [visible, setVisibility] = useState(false);
//   return (
//     <div>
//       <button
//         onClick={() => {
//           setVisibility(!visible);
//         }}
//       >
//         {visible ? "숨기기" : "보이기"}
//       </button>
//       <hr />
//       {visible && <Info />}
//     </div>
//   );
// };

const App = () => {
  return <Average />;
};

export default App;
