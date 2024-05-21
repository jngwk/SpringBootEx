import React, {useState} from "react";

function SignUp(props){
    const [formData, setFormData] = useState({name : '', gender: 'Male'});

    const handleChange = (event) => {
        const {name, value} = event.target;
        setFormData({...formData, [name] : value});
    }
    
    const handleSubmit = (event) => {
        alert(`이름: ${formData.name} \nGender: ${formData.gender}`);
        event.preventDefault();
    }

    return (
        <form onSubmit={handleSubmit}>
            <label>
                Name:
                <input type="text" name="name" value={formData.name} onChange={handleChange}/>
            </label>
            <br/>
            <label>
                Gender:
                <select name="gender" value={formData.gender} onChange={handleChange}>
                    <option selected value="Male">Male</option>
                    <option value="Female">Female</option>
                </select>
            </label>
            <button type="submit">Submit</button>
        </form>
    )
}

export default SignUp;