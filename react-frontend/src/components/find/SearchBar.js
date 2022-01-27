import React from "react";

const SearchBar = ({ setQuery }) => {
  return (
    <div className="search-bar">
      <input
        type="text"
        placeholder="Wprowadź imie, nazwisko lub nazwe uzytkownika.."
        onChange={(e) => setQuery(e.target.value)}
      />
    </div>
  );
};

export default SearchBar;
