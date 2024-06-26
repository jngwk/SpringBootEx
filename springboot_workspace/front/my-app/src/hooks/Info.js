// import React, { useState, useEffect } from "react";

// const Info = () => {
//   const [name, setName] = useState("");
//   const [nickname, setNickname] = useState("");

//   const onChangeName = (e) => {
//     setName(e.target.value);
//   };

//   const onChangeNickname = (e) => {
//     setNickname(e.target.value);
//   };

//   useEffect(() => {
//     console.log({
//       name,
//       nickname,
//     });
//     return () => {
//       console.log("cleanup");
//       console.log({
//         name,
//         nickname,
//       });
//     };
//   }, []);

//   return (
//     <div>
//       <div>
//         <input value={name} onChange={onChangeName} />
//         <input value={nickname} onChange={onChangeNickname} />
//       </div>
//       <div>
//         <div>
//           <b>이름:</b> {name}
//         </div>
//         <div>
//           <b>닉네임: </b>
//           {nickname}
//         </div>
//       </div>
//     </div>
//   );
// };

// export default Info;

import React, { useReducer } from "react";

function reducer(state, action) {
  console.log("state", state);
  console.log("action.name", action.name);
  return {
    ...state,
    [action.name]: action.value,
  };
}

const Info = () => {
  const [state, dispatch] = useReducer(reducer, {
    name: "",
    nickname: "",
  });
  const { name, nickname } = state;
  const onChange = (e) => {
    console.log("e.target", e.target);
    dispatch(e.target);
  };

  return (
    <div>
      <div>
        <input name="name" value={name} onChange={onChange} />
        <input name="nickname" value={nickname} onChange={onChange} />
      </div>
      <div>
        <div>
          <b>Name: </b> {name}
        </div>
        <div>
          <b>Nickname: </b> {nickname}
        </div>
      </div>
    </div>
  );
};

export default Info;
